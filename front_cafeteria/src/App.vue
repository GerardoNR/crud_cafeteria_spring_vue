<script setup>
import { ref,onMounted } from 'vue';
import axios from 'axios';

const clientes = ref ({});
const cargarclientes = async () => {

  const response = await axios.get('http://localhost:8080/cafeteria/mostrar-cafeteria')
  clientes.value = response.data;
  console.log(clientes.value);

}

onMounted(cargarclientes);
</script>


<template>
  <div class="container">
    <button class="btn btn-primary">boton</button>
  </div>
</template>


<style scoped>

</style>
