<template>
  <v-app id="inspire">
    <v-app-bar>

      <v-app-bar-title>Global TI Bank - Avaliação Pratica Java</v-app-bar-title>
      <v-spacer></v-spacer>

      <div>
        <div class="d-flex flex-row-reverse bg-surface-variant2 pa-2 ">
          <div class="mt-6" style="width: 250px;">
            <v-text-field variant="outlined" label="Informe o número da conta" density="compact"
              v-model="campoNumeroConta" type="number"></v-text-field>
          </div>
        </div>
      </div>

      <div>
        <div class="d-flex flex-row-reverse bg-surface-variant2 pa-2 ">
          <div class="pa-2" style="width: 100px;">
            <v-btn class="bg-blue-darken-2" @click="acessarConta()">Acessar</v-btn>
          </div>
        </div>
      </div>
    </v-app-bar>

    <v-main class="bg-grey-lighten-2">
      <v-container>
        <v-row v-if="menssagem !=  ''">
          <v-col>
            <div>
              <v-alert v-model="alert" border="start" close-label="Close Alert" color="red"  variant="tonal" >
                  {{ menssagem }}
              </v-alert>

            </div>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <h1>Olá, seja bem vindo!</h1>
            <h4>Informe o número da sua conta para efeturar suas Transferências e gerar seus extratos</h4>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>
<script>
import { api } from '@/services/api'

export default {
  name: 'Home',
  data: () => ({
    contas: [],
    campoNumeroConta: 0,
    menssagem: ""
  }),
  methods: {
    acessarConta() {
      const contaExiste = this.contas.some(conta => conta.numeroConta == this.campoNumeroConta);

      if (contaExiste) {
        this.$router.push({
          name: 'Bank',
          params: { numero: this.campoNumeroConta }
        });
      } else {
        this.menssagem = "Conta não encontrada";
        console.log("Conta não encontrada");
      }
    },
    async buscarContas() {
      try {
        const response = await api.get('/contas')
        console.log(response.data)
        this.contas = response.data
      } catch (error) {
        console.error('Erro:', error)
      } finally {
        this.loading = false
      }
    },
  },
  mounted() {
    this.buscarContas()
  }
}
</script>