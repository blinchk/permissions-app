<template>
  <v-container>
    <v-row v-if="loading" class="d-flex justify-center mt-5">
      <v-progress-circular indeterminate></v-progress-circular>
    </v-row>
    <v-simple-table v-else>
      <template v-slot:default>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Parent ID</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="permission in permissions" :key="permission.id">
            <td>{{ permission.id }}</td>
            <td>{{ permission.name }}</td>
            <td>{{ parentString(permission) }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import PermissionsClient from "../clients/PermissionsClient";
import Permission from "@/entities/Permission";

@Component
export default class PermissionsView extends Vue {
  permissions: Array<Permission> = [];
  loading = false;

  parentString(permission: Permission): string {
    if (permission.parent) {
      if (permission.parent.parent) {
        return `${permission.parent.id} > ${permission.parent.parent.id}`;
      }
      return permission.parent.id.toString();
    }
    return "None";
  }

  mounted() {
    const client = new PermissionsClient();
    this.loading = true;
    client.getAll().then((permissions) => {
      this.permissions = permissions;
      this.loading = false;
    });
  }
}
</script>

<style scoped></style>
