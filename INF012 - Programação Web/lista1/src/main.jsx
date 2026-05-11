import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import Rotas from './routes'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Rotas />
  </StrictMode>,
)

/*
Roteiro:

main -> importa o componente <Rotas/>
Rotas -> importa os componentes que criam a interface das páginas e define uma rota para eles
Páginas -> criam a interface visual
*/
