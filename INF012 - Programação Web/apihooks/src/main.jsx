import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './components/App/index2'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
