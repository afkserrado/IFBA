import { Component } from 'react';

import { useParams, Link } from 'react-router-dom';

import api from '../../services/API';

import './index.css';

class CourseDetails extends Component {

    // 1. Construtor
    constructor(props) {
        super(props);
        this.state = {
            course: {}
        }
    }

    // 3. Implementa 'componentDidMount' para fazer
    // a requisição HTTP cada vez que a página for montada
    componentDidMount() {
        let id = this.props.id;

        // Requisição HTTP com Axios
        api.get(`/courses/${id}`)
            .then(response => this.setState({ course: response.data }))
            .catch(error => console.error('Erro ao buscar curso:', error));
    }

    // 4. Implementa o 'render()' para renderizar a
    // interface visual
    render() {
        return(
            <div className='conteiner'>
                <h1 className='title'>{this.state.course.name}</h1>
                <ul className='list'>
                    <li>Categoria: {this.state.course.category}</li>
                    <li>Instrutor: {this.state.course.instructor}</li>
                    <li>Carga horária: {this.state.course.duration}</li>
                    <li>Estudantes: {this.state.course.students}</li>
                    <li>Descrição: {this.state.course.description}</li>
                </ul>

                <Link className='button' to='/'>Home</Link>
            </div>
        );
    }
}

// 2. Função para obter o 'id' da URL acessada
function CourseDetailsWrapper() {
    const { id } = useParams(); // Obtém o 'id' da URL acessada

    // Renderiza o componente de classe CourseDetails, 
    // passando o id da URL como uma prop
    return <CourseDetails id={id}/>;
} 

export default CourseDetailsWrapper;

/*
Fluxo: 
1. O usuário acessa /courses/1
2. O React Router renderiza CourseDetailsWrapper (export default)
3. CourseDetailsWrapper executa useParams() e captura o id = '1'
4. CourseDetailsWrapper renderiza <CourseDetails id='1'/>
5. A classe CourseDetails é montada
6. componentDidMount é executado após a montagem, fazendo a requisição para /courses/1 e salva os dados no state
7. render exibe os dados na tela.
*/

