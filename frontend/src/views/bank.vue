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
            <h1>Olá, seja bem vindo! <span :class="displayRecurcos">Usuário</span> </h1>
          </v-col>
        </v-row>
        <v-row >
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

  }),
  methods: {

  }
}
</script>