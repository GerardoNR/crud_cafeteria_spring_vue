<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import Swal from 'sweetalert2';

const clientes = ref([]);
const nuevocliente = ref({
  nombre: '',
  apellido: '',
  telefono: '',
  email: '',
  imagenURL: ''
});

const editado = ref(false);

const cargarclientes = async () => {
  try {
    const response = await axios.get('http://localhost:8080/cafeteria/mostrar-cafeteria');
    clientes.value = response.data;
  } catch (error) {
    console.error('Error al cargar clientes:', error);
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'No se pudieron cargar los clientes'
    });
  }
}

const agregarcliente = async () => {
  try {
    if (editado.value) {
      const response = await axios.put(`http://localhost:8080/cafeteria/editar-cliente/${nuevocliente.value.id}`, nuevocliente.value);
      
     
      const index = clientes.value.findIndex(c => c.id === nuevocliente.value.id);
      if (index !== -1) {
        clientes.value[index] = { ...response.data };
      }
      
      editado.value = false;
      Swal.fire({
        icon: 'success',
        title: 'Cliente actualizado correctamente',
        showConfirmButton: false,
        timer: 1500
      });
    } else {
      const response = await axios.post('http://localhost:8080/cafeteria/insertar-cliente', nuevocliente.value);
      clientes.value.unshift(response.data);
      Swal.fire({
        icon: 'success',
        title: 'Cliente agregado correctamente',
        showConfirmButton: false,
        timer: 1500
      });
    }
    
    nuevocliente.value = {
      nombre: '',
      apellido: '',
      telefono: '',
      email: '',
      imagenURL: ''
    };
    
  } catch (error) {
    console.error('Error:', error);
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'Ocurrió un error al procesar la solicitud',
    });
  }
}

const editarcliente = (cliente) => {
  Object.assign(nuevocliente.value, cliente);
  editado.value = true;
}

const eliminarcliente = async (id) => {
  Swal.fire({
    title: '¿Estás seguro de eliminar el cliente?',
    text: 'No podrás revertir esto',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3845d6',
    cancelButtonColor: '#c33',
    confirmButtonText: 'Sí, eliminarlo'
  }).then(async (result) => {
    if (result.isConfirmed) {
      await eliminarclientePorId(id);
    }
  });
}

const eliminarclientePorId = async (id) => {
  try {
    await axios.delete(`http://localhost:8080/cafeteria/eliminar-cliente/${id}`);
    clientes.value = clientes.value.filter(cliente => cliente.id !== id);
    Swal.fire({
      icon: 'success',
      title: 'Cliente eliminado correctamente',
      showConfirmButton: false,
      timer: 1500
    });
  } catch (error) {
    console.error('Error al eliminar el cliente:', error);
    Swal.fire({
      icon: 'error',
      title: 'Error al eliminar el cliente',
      text: 'No se puede eliminar el cliente',
    });
  }
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
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombre" v-model="nuevocliente.nombre" required>
              </div>
              <div class="col-md-6 mb-3">
                <label for="apellido" class="form-label">Apellido</label>
                <input type="text" class="form-control" id="apellido" v-model="nuevocliente.apellido" required>
              </div>
              <div class="col-md-6 mb-3">
                <label for="telefono" class="form-label">Teléfono</label>
                <input type="number" class="form-control" id="telefono" v-model="nuevocliente.telefono" required>
              </div>
              <div class="col-md-6 mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" v-model="nuevocliente.email" required>
              </div>
              <div class="col-md-6 mb-3">
                <label for="imagenURL" class="form-label">Imagen URL</label>
                <input type="url" class="form-control" id="imagenURL" v-model="nuevocliente.imagenURL" required>
              </div>
            </div>
            <button type="submit" class="btn btn-danger">
              {{ editado ? 'Actualizar cliente' : 'Agregar cliente' }}
            </button>
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
                  <th scope="col">Teléfono</th>
                  <th scope="col">Email</th>
                  <th scope="col">Imagen</th>
                  <th scope="col">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="cliente in clientes" :key="cliente.id">
                  <td>{{ cliente.id }}</td>
                  <td>{{ cliente.nombre }}</td>
                  <td>{{ cliente.apellido }}</td>
                  <td>{{ cliente.telefono }}</td>
                  <td>{{ cliente.email }}</td>
                  <td><img :src="cliente.imagenURL" alt="imagen de cliente" width="50" height="50" class="img-thumbnail"></td>
                  <td>
                    <button @click="eliminarcliente(cliente.id)" class="btn btn-danger mx-2">
                      <i class="bi bi-trash2-fill"></i>
                    </button>
                    <button @click="editarcliente(cliente)" class="btn btn-warning">
                      <i class="bi bi-pencil-fill"></i>
                    </button>
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
.card {
  background-color: #f5e7d9; /* Fondo café claro */
  border: 1px solid #d2b48c; /* Borde café medio */
}

.table thead th {
  background-color: #8b4513; /* Café oscuro para encabezados */
  color: white;
}

.btn-danger {
  background-color: #a52a2a; /* Rojo café */
  border-color: #8b0000;
}

.btn-warning {
  background-color: #d2b48c; /* Café claro */
  border-color: #bc8f8f;
  color: #333;
}
</style>