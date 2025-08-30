/**
 * plugins/index.js
 *
 * Automatically included in `./src/main.js`
 */

// Plugins
import vuetify from "./vuetify";
import router from "@/router/index";
import axiosPlugin from "@/services/api";

export function registerPlugins(app) {
  app.use(router);
  app.use(axiosPlugin);
  app.use(vuetify);
}
