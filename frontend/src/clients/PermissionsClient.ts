import axios from "axios";
import { API_URL } from "@/utils/Globals";
import Permission from "@/entities/Permission";

export default class PermissionsClient {
  getAll(): Promise<Array<Permission>> {
    return new Promise<Array<Permission>>((resolve, reject) => {
      axios
        .get(API_URL + "/permission")
        .then(({ data }) => {
          if (data) {
            resolve(data as Array<Permission>);
          }
        })
        .catch(reject);
    });
  }
  getById(id: number): Promise<Permission> {
    return new Promise<Permission>((resolve, reject) => {
      axios
        .get(API_URL + `/permission/${id}`)
        .then(({ data }) => {
          if (data) {
            resolve(data as Permission);
          }
        })
        .catch(reject);
    });
  }
  create(permission: Permission): Promise<Permission> {
    return new Promise<Permission>((resolve, reject) => {
      axios
        .post(API_URL + `/permission`, permission)
        .then(({ data }) => {
          if (data) {
            resolve(data as Permission);
          }
        })
        .catch(reject);
    });
  }
}
