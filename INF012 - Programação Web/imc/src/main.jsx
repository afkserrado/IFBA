// Define o ponto de entrada da aplicação React
// Conecta o código React com o HTML da página

import { StrictMode } from 'react' // Alerta sobre práticas problemáticas
import { createRoot } from 'react-dom/client' // Cria a raiz da aplicação
import IMC from './components/imc/index.jsx' // Componente

// Pega a tag de id 'root' do index.html: document.getElementById('root')
// Cria uma raiz: createRoot(...)
// Injeta o componente: <IMC/>
// Renderiza: conteúdo aparece na tela
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <IMC/>
  </StrictMode>,
)
