import { Link } from 'react-router-dom';

function Home() {
    return(
        <div>
            <h1>Lista de exercícios 1</h1>
            <span>Selecione a questão abaixo: </span>
            <ul>
                <li> <Link to='/q2'>Questão 2</Link> </li>
                <li> <Link to='/q3-4'>Questões 3 e 4</Link> </li>
            </ul>
        </div>
    );
} export default Home;