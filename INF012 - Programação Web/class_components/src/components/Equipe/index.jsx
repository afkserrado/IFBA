import React, { Component } from 'react';

class Equipe extends Component {
    
    // O React já faz internamente new Equipe(props)
    render() {
        return(
            <div>
                <h1>Nome: {this.props.nome}</h1>
            </div>
        );
    }
} export default Equipe;