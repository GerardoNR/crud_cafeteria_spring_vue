import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    fs: {
      // Permite servir archivos desde estos directorios
      allow: ['..'], // o la ruta específica que necesites
    }
  }
})
