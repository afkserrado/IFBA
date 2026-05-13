import { useState, useEffect } from 'react';

import api from '../../services/API';

import { Link } from 'react-router-dom';

function CourseList() {

    // 1. Define as variáveis necessárias
    const [courses, setCourses] = useState([]);

    // 2. Implementa useEffect() recuperar os dados
    // sempre que o componente for remontado
    useEffect(() => {
        // 3. Requisição HTTP com Axios
        api.get('/courses') // Página de onde os dados serão extraídos
            .then(response => setCourses(response.data))
            .catch(error => console.error('Erro ao buscar cursos:', error));
    }, []);

    // 4. Implementa a interface com HTML
    return(
        <div>
            <h1>Course Explorer</h1>

            <nav>
                <Link to='/'>Home</Link>
            </nav>

            <h2>Cursos disponíveis</h2>

            {/* Lista de cursos */}
            <ul>
                {/* Percorre cada item de 'courses' */}
                {courses.map(course => {
                    // Retorna cada item de 'courses'
                    return(
                        <li key={course.id}>
                            <strong>{course.id}</strong> - {course.name} | {course.category} | <Link to={`/courses/${course.id}`}>Detalhar</Link>
                        </li>
                    );
                })}
            </ul>
        </div>
    );
} 

export default CourseList;