<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <v-toolbar-title>
        <span>Permissions</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn icon to="/">
          <v-icon> mdi-format-list-bulleted-type</v-icon>
        </v-btn>
        <v-btn icon to="/create">
          <v-icon> mdi-plus</v-icon>
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "App",

  data: () => ({
    //
  }),
});
</script>

<style lang="scss">
.logo {
  color: white;
}
</style>
