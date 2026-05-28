import { Link } from 'react-router-dom';

function Menu() {
    return(
        <nav>
            <Link to="/">Home</Link>
            <> </>
            <Link to="/sobre">Sobre</Link>
        </nav>
    );
} export default Menu;