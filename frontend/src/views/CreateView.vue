<template>
  <v-container>
    <v-row v-if="loading.parents" class="d-flex justify-center mt-5">
      <v-progress-circular indeterminate></v-progress-circular>
    </v-row>
    <v-card v-else>
      <v-card-title>Create new permission</v-card-title>
      <v-form @submit.prevent="save">
        <v-card-text>
          <v-text-field
            label="Name"
            counter
            v-model="newPermission.name"
            :rules="nameRules"
          ></v-text-field>
          <v-select
            label="Parent Permission"
            :loading="loading.parents"
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
          <v-btn
            type="submit"
            color="success"
            depressed
            block
            :disabled="!isNameValid"
            :loading="loading.save"
            >Submit</v-btn
          >
        </v-card-actions>
      </v-form>
    </v-card>
  </v-container>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import PermissionsClient from "@/clients/PermissionsClient";
import Permission from "@/entities/Permission";
import CreateViewLoadings from "@/entities/CreateViewLoadings";

@Component
export default class CreateView extends Vue {
  permissions: Array<Permission> = [];
  loading: CreateViewLoadings = {
    save: false,
    parents: false,
  };
  newPermission: Permission = {
    name: "",
    parent: null,
  };

  nameRules = [
    (v: string) => !!v || "Name is required",
    (v: string) => v.length >= 3 || "Name must contain at least 3 characters.",
  ];

  get availableParents() {
    return this.permissions.filter((permission) => !permission.parent?.parent);
  }

  get isNameValid() {
    return this.newPermission.name && this.newPermission.name.length >= 3;
  }

  mounted() {
    const client = new PermissionsClient();
    this.$set(this.loading, "parents", true);
    client
      .getAll()
      .then((permissions) => {
        this.permissions = permissions;
        this.$delete(this.loading, "parents");
      })
      .catch(() => {
        this.$delete(this.loading, "parents");
      });
  }

  save() {
    const client = new PermissionsClient();
    this.$set(this.loading, "save", true);
    client
      .create(this.newPermission)
      .then(() => {
        this.$delete(this.loading, "save");
        this.$router.push("/");
      })
      .catch(() => {
        this.$delete(this.loading, "save");
      });
  }
}
</script>

<style scoped></style>
