import { useParams, Link } from 'react-router-dom';

function Sobre() {

    // Desestruturação:
    // useParams() retorna um objeto com os parâmetros da URL
    const {id} = useParams();

    return(
        <div>
            <Link to="/">Home</Link>
            <h1>Página Sobre</h1>
            {id !== undefined 
                ? <p>Essa é a página Sobre com id = {id}</p>
                : ""
            }
        </div>
    );
} export default Sobre;