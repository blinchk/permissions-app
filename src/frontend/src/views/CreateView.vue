<template>
  <v-container>
    <v-row v-if="loading" class="d-flex justify-center mt-5">
      <v-progress-circular indeterminate></v-progress-circular>
    </v-row>
    <v-card v-else>
      <v-card-title>Create new permission</v-card-title>
      <v-form @submit.prevent>
        <v-card-text>
          <v-text-field
            label="Name"
            counter
            v-model="newPermission.name"
          ></v-text-field>
          <v-select
            label="Parent Permission"
            :loading="loading"
            :items="availableParents"
            v-model="newPermission.parent"
            item-value="id"
            item-text="name"
            return-object
            clearable
          >
            <template v-slot:selection="{ item }">
              <span>{{ item.id }} - {{ item.name }}</span>
            </template>
            <template v-slot:item="{ item }">
              <span>{{ item.id }} - {{ item.name }}</span>
            </template>
          </v-select>
        </v-card-text>
        <v-card-actions>
          <v-btn type="submit" color="success" depressed block>Submit</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-container>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import PermissionsClient from "@/clients/PermissionsClient";
import Permission from "@/entities/Permission";

@Component
export default class CreateView extends Vue {
  permissions: Array<Permission> = [];
  loading = false;
  parent: Permission = null;
  newPermission: Permission = {
    name: "",
    parent: null,
  };

  get availableParents() {
    return this.permissions.filter((permission) => !permission.parent?.parent);
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
