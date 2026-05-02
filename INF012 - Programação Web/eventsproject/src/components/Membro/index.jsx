import { Component } from 'react';

class Membro extends Component {
    
    constructor(props) {
        super(props);
        
        this.state = {
            nome: props.nome,
        }

        this.logar = this.logar.bind(this);
    }

    logar(valor) {
        this.setState( {nome: valor });
    }

    render() {
        return(
            <div>
                <h2>Seja bem-vindo {this.state.nome}</h2>
                <button onClick = {() => this.logar("João")}>Logar como</button>
                <button onClick = {() => this.setState({ nome: "" })}>Sair</button>
            </div>
        );
    }
} export default Membro;