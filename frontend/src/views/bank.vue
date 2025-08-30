<template>
  <v-app id="inspire">
    <v-app-bar>

      <v-app-bar-title>Global TI Bank - Avaliação Pratica Java</v-app-bar-title>
      <v-spacer></v-spacer>

       <div :class="hideRecurcos">
        <div class="d-flex flex-row-reverse bg-surface-variant2 pa-2 ">
          <div class="mt-6" style="width: 250px;">
            <v-text-field variant="outlined" label="Informe o número da conta" density="compact"></v-text-field>
          </div>
        </div>
      </div>

      <div :class="hideRecurcos">
        <div class="d-flex flex-row-reverse bg-surface-variant2 pa-2 ">
          <div class="pa-2" style="width: 100px;">
            <v-btn class="bg-blue-darken-2" @click="mostrarDisplay()">Acessar</v-btn>
          </div>
        </div>
      </div>

      <div :class="displayRecurcos">
        <div class="d-flex flex-row-reverse bg-surface-variant2 pa-2 ">
          <div class="pa-2" style="width: 100px;">
            <v-btn class="bg-orange-darken-2" @click="ocultarDisplay()">Sair</v-btn>
          </div>
        </div>
      </div>

    </v-app-bar>

    <v-main class="bg-grey-lighten-2">
      <v-container>
        <v-row>
          <v-col>
            <h1>Olá, seja bem vindo! <span :class="displayRecurcos">Usuário</span> </h1>
            <h4 :class="hideRecurcos">Informe o número da sua conta para efeturar suas Transferências e gerar seus extratos</h4>
          </v-col>
        </v-row>
        <v-row :class="displayRecurcos">
            <v-col class="mt-2" cols="12">
              <strong>Operações</strong>
            </v-col>
            <v-col cols="6" sm="4" md="3">
              <v-card :border="5" link hover @click="$router.push('/transferencia')">
                <template v-slot:title>
                  <div class="d-flex justify-center mb-6">
                    Transferência
                  </div>
                </template>
                <template v-slot:text>
                  <div class="d-flex justify-center mb-6">
                    <v-icon size="34">mdi-swap-horizontal</v-icon>
                  </div>
                </template>
              </v-card>
            </v-col>
            <v-col cols="6" sm="4" md="3">
              <v-card :border="5" link hover @click="$router.push('/extrato')">
                <template v-slot:title>
                  <div class="d-flex justify-center mb-6">
                    Extrato
                  </div>
                </template>
                <template v-slot:text>
                  <div class="d-flex justify-center mb-6">
                    <v-icon size="34">mdi-note-text-outline</v-icon>
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
export default {
  data: () => ({
    displayRecurcos: "d-none",
    hideRecurcos: "inline"
  }),
  methods: {
    mostrarDisplay() {
      this.displayRecurcos = "inline";
      this.hideRecurcos = "d-none";
    },
    ocultarDisplay() {
      this.displayRecurcos = "d-none";
      this.hideRecurcos = "inline";
    }
  }
}
</script>