<template>
  <v-app id="inspire">
    <v-app-bar>
      <v-app-bar-title>Global TI Bank - Avaliação Pratica Java</v-app-bar-title>
      <v-spacer></v-spacer>
      <div :class="displayRecurcos">
        <div class="d-flex flex-row-reverse bg-surface-variant2 pa-2 ">
          <div class="pa-2" style="width: 100px;">
            <v-btn class="bg-orange-darken-2" @click="$router.push('/')">Sair</v-btn>
          </div>
        </div>
      </div>
    </v-app-bar>

    <v-main class="bg-grey-lighten-2">
      <v-container>
        <v-row>
          <v-col>
            <h1>Olá, seja bem vindo(a)! <strong>{{ usuario }}</strong> </h1>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="mt-2" cols="12">
            <strong>Operações na conta {{ numero }}</strong>
          </v-col>
          
          <v-col cols="6" sm="4" md="3">
            <v-card :border="5" link hover @click="transferir()">
              <template v-slot:title>
                <div class="d-flex justify-center mb-6">Transferência</div>
              </template>
              <template v-slot:text>
                <div class="d-flex justify-center mb-6">
                  <v-icon size="34">mdi-swap-horizontal</v-icon>
                </div>
              </template>
            </v-card>
          </v-col>
          
          <v-col cols="6" sm="4" md="3">
            <v-card :border="5" link hover @click="gerarExtrato(idConta)">
              <template v-slot:title>
                <div class="d-flex justify-center mb-6">Extrato</div>
              </template>
              <template v-slot:text>
                <div class="d-flex justify-center mb-6">
                  <v-icon size="34">mdi-note-text-outline</v-icon>
                </div>
              </template>
            </v-card>
          </v-col>

          <!-- Tela de Transferência -->
          <v-col cols="12" md="6" v-if="mostrarTelaTransferencia">
            <v-card :border="5" hover>
              <template v-slot:title>
                <div class="d-flex justify-center mb-6">Transferência</div>
              </template>
              <template v-slot:text>
                <v-form v-model="valid">
                  <v-container>
                    <v-row>
                      <v-col cols="12" md="6">
                        <v-text-field v-model="contaDestino" :rules="nameRules" 
                          label="Numero da conta destino" required type="number"></v-text-field>
                      </v-col>
                      <v-col cols="12" md="6">
                        <v-text-field v-model="valorTransferencia" :rules="nameRules" 
                          label="R$" required type="number"></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12" md="6">
                        <v-text-field v-model="dataTransferencia" :rules="emailRules" 
                          label="Data" type="date" required></v-text-field>
                      </v-col>
                      <v-col cols="12" md="6">
                        <v-btn class="mt-3 ml-5" variant="tonal" color="blue-darken-4">
                          Efetuar transação
                        </v-btn>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-form>
              </template>
            </v-card>
          </v-col>

          <!-- Tela de Extrato -->
          <v-col cols="12" md="6" v-if="mostrarTelaExtrato">
            <v-card :border="5" hover>
              <template v-slot:title>
                <div class="d-flex justify-center mb-6">Extrato</div>
              </template>
              <template v-slot:text>
                <div v-if="historicoExtrato.length === 0" class="text-center">
                  Nenhuma transferência encontrada
                </div>
                <div v-else>
                  <v-list lines="two">
                    <v-list-item v-for="(item, index) in historicoExtrato" :key="index">
                      <template v-slot:prepend>
                        <v-icon :color="item.valor > 0 ? 'green' : 'red'">
                          {{ item.valor > 0 ? 'mdi-arrow-up' : 'mdi-arrow-down' }}
                        </v-icon>
                      </template>
                      
                      <v-list-item-title>
                        {{ item.descricao || 'Transferência' }}
                      </v-list-item-title>
                      
                      <v-list-item-subtitle>
                        Data: {{ formatarData(item.dataTransferencia) }}
                      </v-list-item-subtitle>
                      
                      <template v-slot:append>
                        <span :class="{'text-green': item.valor > 0, 'text-red': item.valor < 0}">
                          R$ {{ Math.abs(item.valor).toFixed(2) }}
                        </span>
                      </template>
                    </v-list-item>
                  </v-list>
                </div>
              </template>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { api } from '@/services/api'

export default {
  props: ['numero'],
  data: () => ({
    idConta: null,
    usuario: null,
    mostrarTelaTransferencia: false,
    mostrarTelaExtrato: false,
    historicoExtrato: [],
    
    contaDestino: '',
    valorTransferencia: '',
    dataTransferencia: '',
    valid: false,
    
    nameRules: [
      value => !!value || 'Campo obrigatório',
      value => (value && value.length >= 1) || 'Mínimo 1 caractere',
    ],
    emailRules: [
      value => !!value || 'Campo obrigatório',
    ],
  }),
  methods: {
    gerarExtrato(id) {
      this.mostrarTelaExtrato = !this.mostrarTelaExtrato;
      if (this.mostrarTelaExtrato) {
        this.buscarHistoricoTransferencia(id);
      }
    },
    
    transferir() {
      this.mostrarTelaTransferencia = !this.mostrarTelaTransferencia;
      this.mostrarTelaExtrato = false;
    },
    
    async buscarContas() {
      try {
        const response = await api.get(`/contas/numero/${this.numero}`)
        this.usuario = response.data.usuario
        this.idConta = response.data.id
      } catch (error) {
        console.error('Erro:', error)
      }
    },
    
    async buscarHistoricoTransferencia(id) {
      try {
        const response = await api.get(`/transferencias/conta-origem/${id}`)
        this.historicoExtrato = response.data 
        console.log("Extrato:", this.historicoExtrato) 
        
        if (this.historicoExtrato && !Array.isArray(this.historicoExtrato)) {
          console.log("Estrutura do extrato:", this.historicoExtrato)
          this.historicoExtrato = [this.historicoExtrato] 
        }
      } catch (error) {
        console.error('Erro ao buscar extrato:', error)
        this.historicoExtrato = []
      }
    },
    
    formatarData(data) {
      if (!data) return '';
      return new Date(data).toLocaleDateString('pt-BR');
    }
  },
  mounted() {
    this.buscarContas()
  }
}
</script>

<style>
.text-green {
  color: green;
  font-weight: bold;
}
.text-red {
  color: red;
  font-weight: bold;
}
</style>