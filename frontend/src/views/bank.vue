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
        <v-row v-if="menssagem !=  ''">
          <v-col>
            <div>
              <v-alert v-model="alert" border="start" :color="corMensagem"  variant="tonal" >
                  {{ menssagem }}
              </v-alert>

            </div>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <h1>Olá, seja bem vindo(a)! <strong>{{ usuario }}</strong> </h1>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="mt-2" cols="12">
            <strong>Operações na conta {{ numero }}</strong>
            <br>
            <strong class="text-green">Saldo: {{ saldoFormatado }}</strong>
          </v-col>

          <v-col cols="6" sm="4" md="3">
            <v-card :border="5" link hover @click="mostrarTelaTransf()">
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
                        <v-text-field v-model="contaDestino" :rules="nameRules" label="Numero da conta destino" required
                          type="number"></v-text-field>
                      </v-col>
                      <v-col cols="12" md="6">
                        <v-text-field v-model="valorTransferencia" :rules="nameRules" label="R$" required
                          type="number" step="0.01" min="0.01"></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12" md="6">
                        <v-text-field v-model="dataTransferencia" :rules="emailRules" label="Data" type="date"
                          required></v-text-field>
                      </v-col>
                      <v-col cols="12" md="6">
                        <v-btn class="mt-3 ml-5" variant="tonal" color="blue-darken-4" @click="transferir()" :loading="loadingTransferencia">
                          Efetuar transação
                        </v-btn>
                        <v-btn class="mt-3 ml-5" variant="outlined" color="red" @click="cancelarTransferencia">
                          Cancelar
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
                        <span :class="{ 'text-green': item.valor > 0, 'text-red': item.valor < 0 }">
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
    idContaDestino: null,
    saldo: null,
    usuario: null,
    mostrarTelaTransferencia: false,
    mostrarTelaExtrato: false,
    historicoExtrato: [],
    loadingTransferencia: false,
    menssagem: '',
    corMensagem: 'red',
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
  computed: {
    saldoFormatado() {
      return new Intl.NumberFormat('pt-BR', {
        style: 'currency',
        currency: 'BRL'
      }).format(this.saldo);
    }
  },
  methods: {
    gerarExtrato(id) {
      this.mostrarTelaExtrato = !this.mostrarTelaExtrato;
      if (this.mostrarTelaExtrato) {
        this.buscarHistoricoTransferencia(id);
      }
    },
    
    mostrarTelaTransf() {
      this.mostrarTelaTransferencia = !this.mostrarTelaTransferencia;
      this.mostrarTelaExtrato = false;
    },
    
    cancelarTransferencia() {
      this.mostrarTelaTransferencia = false;
      this.contaDestino = '';
      this.valorTransferencia = '';
      this.dataTransferencia = this.getDataAtualFormatada();
    },
    
    async transferir() {
      if (!this.contaDestino || !this.valorTransferencia || !this.dataTransferencia) {
        this.menssagem = 'Por favor, preencha todos os campos';
        return;
      }

      const valor = parseFloat(this.valorTransferencia);
      if (isNaN(valor) || valor <= 0) {
        this.menssagem = 'Por favor, informe um valor válido e positivo';
          this.corMensagem = 'red';
        return;
      }

      if (valor > this.saldo) {
        this.menssagem = 'Saldo insuficiente para realizar a transferência';
          this.corMensagem = 'red';
        return;
      }

      if (parseInt(this.contaDestino) == this.numero) {
        this.menssagem = 'Não é possível transferir para a própria conta';
          this.corMensagem = 'red';
        return;
      }

      this.loadingTransferencia = true;

      try {
        const idContaDestino = await this.buscarContasDestino(this.contaDestino);
        
        if (!idContaDestino) {
          this.menssagem = 'Conta destino não encontrada';
          this.loadingTransferencia = false;
          this.corMensagem = 'red';
          return;
        }

        console.log('Conta destino:', this.contaDestino);
        console.log('ID Conta destino:', idContaDestino);
        console.log('Valor:', valor);
        console.log('Data:', this.dataTransferencia);

        const dadosTransferencia = {
          contaOrigem: this.idConta, 
          contaDestino: idContaDestino,
          valor: valor,
          dataTransferencia: this.dataTransferencia
        };

        console.log('Dados enviados:', JSON.stringify(dadosTransferencia, null, 2));

        const response = await api.post('/transferencias', dadosTransferencia);
        console.log('Resposta da API:', response.data);

        await this.buscarSaldoAtualizado();
        
        this.contaDestino = '';
        this.valorTransferencia = '';
        this.dataTransferencia = this.getDataAtualFormatada();

        this.menssagem = 'Transferência realizada com sucesso!';
        this.corMensagem = 'green';
        
        this.mostrarTelaTransferencia = false;

      } catch (error) {
        console.error('Erro ao realizar transferência:', error);

        if (error.response) {
          console.error('Detalhes do erro:', error.response.data);
          
          if (error.response.status === 400) {
            if (error.response.data.message) {
              this.menssagem =  `Erro: ${error.response.data.message}`;
              this.corMensagem = 'red';
            } else {
              this.menssagem = error.response.data.error; 
              this.corMensagem = 'red';
            }
          } else if (error.response.status === 404) {
            this.menssagem = 'Conta destino não encontrada.';
            this.corMensagem = 'red';
          } else if (error.response.status === 500) {
            this.menssagem = 'Erro interno do servidor. Tente novamente mais tarde.';
            this.corMensagem = 'red';
          } else {
            this.menssagem = 'Erro ao realizar transferência. Tente novamente.';
            this.corMensagem = 'red';
          }
        } else {
          this.menssagem = 'Erro de conexão. Tente novamente.';
          this.corMensagem = 'red';
        }
      } finally {
        this.loadingTransferencia = false;
      }
    },

    async buscarSaldoAtualizado() {
      try {
        const response = await api.get(`/contas/numero/${this.numero}`);
        this.saldo = response.data.saldo;
        console.log('Saldo atualizado:', this.saldo);
      } catch (error) {
        console.error('Erro ao buscar saldo atualizado:', error);
        this.menssagem = 'Transferência realizada, mas não foi possível atualizar o saldo. Recarregue a página.';
      }
    },

    async buscarContasDestino(numero) {
      try {
        const response = await api.get(`/contas/numero/${numero}`)
        console.log('Dados da conta Destino:', response.data)
        return response.data.id;
      } catch (error) {
        console.error('Erro ao buscar conta destino:', error)
        return null;
      }
    },

    async buscarContas(numero) {
      try {
        const response = await api.get(`/contas/numero/${numero}`)
        this.usuario = response.data.usuario
        this.saldo = response.data.saldo
        this.idConta = response.data.id
        console.log('Dados da conta:', response.data)
      } catch (error) {
        console.error('Erro ao buscar conta:', error)
        this.menssagem = 'Erro ao carregar dados da conta';
      }
    },

    async buscarHistoricoTransferencia(id) {
      try {
        const response = await api.get(`/transferencias/conta-origem/${id}`)
        this.historicoExtrato = response.data
        console.log('Extrato:', this.historicoExtrato)

        if (this.historicoExtrato && !Array.isArray(this.historicoExtrato)) {
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
    },
    
    getDataAtualFormatada() {
      const today = new Date();
      const year = today.getFullYear();
      const month = String(today.getMonth() + 1).padStart(2, '0');
      const day = String(today.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
    }
  },
  mounted() {
    this.buscarContas(this.numero);
    this.dataTransferencia = this.getDataAtualFormatada();
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