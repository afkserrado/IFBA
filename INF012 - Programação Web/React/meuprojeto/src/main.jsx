// Define o ponto de entrada da aplicação React
// Conecta o código React com o HTML da página

import { StrictMode } from 'react' // Alerta sobre práticas problemáticas
import { createRoot } from 'react-dom/client' // Cria a raiz da aplicação
import Social from './componentes/social'

// Pega a tag de id 'root' do index.html: document.getElementById('root')
// Cria uma raiz: createRoot(...)
// Injeta o componente: <Social />
// Renderiza: conteúdo aparece na tela
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Social nome="Manoel" idade="43" facebook="www.meuface" instagram="@manoelnetom"/>
    <Social nome="Maria" idade="23" facebook="www.meuface" instagram="@Maria"/>
  </StrictMode>,
)
