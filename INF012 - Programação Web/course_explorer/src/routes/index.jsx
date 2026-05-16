import { BrowserRouter, Route, Routes } from 'react-router-dom';

import CourseList from '../pages/Home';
import CourseDetailsWrapper from '../pages/Details';

function Rotas() {
    return(
        <BrowserRouter>          
            <Routes>
                <Route path='/' element={<CourseList/>}/>
                <Route path='/courses/:id' element={<CourseDetailsWrapper/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;