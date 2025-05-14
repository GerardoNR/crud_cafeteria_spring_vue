<script setup>
import { ref,onMounted } from 'vue';
import axios from 'axios';

const clientes = ref ({});
const nuevocliente = ref({
  nombre: '',
  apellido: '',
  telefono: '',
  email: '',
  imagenURL: ''
});

const cargarclientes = async () => {
  const response = await axios.get('http://localhost:8080/cafeteria/mostrar-cafeteria')
  clientes.value = response.data;
  console.log(clientes.value);
}
const agregarcliente = async ()=> {
await axios.post('http://localhost:8080/cafeteria/insertar-cliente',nuevocliente.value);
await cargarclientes();
nuevocliente.value = {
   nombre: '',
  apellido: '',
  telefono: '',
  email: '',
  imagenURL: ''
};

}


onMounted(cargarclientes);
</script>


<template>
  <div class="container">
    <div class="row">
    <div class="col-md-12 mt-4">
    <div class="card shadow p-4 mb-4">
      <h2 class="text-center">Formulario de cliente</h2>
      <form @submit.prevent="agregarcliente">
        <div class="row">
        <div class="col-md-6 mb-3">
        <label for="nombre" class="form-label"> Nombre</label>
        <input type="text" class="form-control" id="nombre" v-model="nuevocliente.nombre" required>
        </div>
        <div class="col-md-6 mb-3">
        <label for="apellido" class="form-label"> Apellido</label>
        <input type="text" class="form-control" id="apellido" v-model="nuevocliente.apellido" required>
        </div>
        <div class="col-md-6 mb-3">
        <label for="telefono" class="form-label"> Telefono</label>
        <input type="number" class="form-control" id="telefono" v-model="nuevocliente.telefono" required>
        </div>
        <div class="col-md-6 mb-3">
        <label for="email" class="form-label"> Email</label>
        <input type="text" class="form-control" id="email" v-model="nuevocliente.email" required>
        </div>
        <div class="col-md-6 mb-3">
        <label for="imagenURL" class="form-label"> Imagen URL</label>
        <input type="text" class="form-control" id="imagenURL" v-model="nuevocliente.imagenURL" required>
        </div>
        </div>
        <button type="submit" class="btn btn-danger">Agregar cliente</button>
      </form>
       </div>
       </div>

      <div class="col-md-12">
        <div class="card shadow">
          <div class="card-body">
            <h5 class="card-title mb-3">Tabla de clientes</h5>
          <table class="table table-hover align-middle">
            <thead class="table-light">
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Nombre</th>
      <th scope="col">Apellido</th>
      <th scope="col">Telefono</th>
      <th scope="col">Email</th>
      <th scope="col">Imagen</th>
      <th scope="col">Acciones</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="cliente in clientes" :key="cliente.id">
      <td>{{ cliente.id}}</td>
      <td>{{ cliente.nombre}}</td>
      <td>{{ cliente.apellido}}</td>
      <td>{{ cliente.telefono}}</td>
      <td>{{ cliente.email}}</td>
      <td><img :src="cliente.imagenURL" alt="imagen de cliente" width="50"></td>
      <td>
        
        <button class="btn btn-danger mx-2"><i class="bi bi-trash2-fill"></i></button>

      </td>
    </tr>
  </tbody>
</table>
          </div>
          </div>


    
  </div>
    </div>
  </div>
</template>


<style scoped>

</style>
