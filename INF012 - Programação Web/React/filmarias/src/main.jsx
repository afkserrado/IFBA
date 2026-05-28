import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import Rotas from './routes';

import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <ToastContainer autoClose={3000} />
    <Rotas/>
  </StrictMode>,
)
