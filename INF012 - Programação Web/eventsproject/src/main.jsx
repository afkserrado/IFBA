import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import Membro from './components/Membro'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Membro />
  </StrictMode>,
)
