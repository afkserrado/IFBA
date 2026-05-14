import React, { Component } from 'react';

class Q2 extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            consumo: 0,
            qtdPessoas: 0,
            percGorjeta: 0,
        };
    }

    render() {
        
        const { consumo, qtdPessoas, percGorjeta } = this.state;
        const totalGeral = consumo * (1 + percGorjeta / 100);
        const totalPorPessoa = qtdPessoas > 0 ? totalGeral / qtdPessoas : 0;
        
        return(
            <div>
                <h1>Questão 2</h1>

                <label>Consumo: </label>
                <input 
                    type='number' 
                    value={this.state.consumo} 
                    onChange={e => this.setState({ consumo: Number(e.target.value) })}
                />
                <br/>

                <label>Quantidade de pessoas: </label>
                <input
                    type='number'
                    value={this.state.qtdPessoas}
                    onChange={e => this.setState({ qtdPessoas: Number(e.target.value) })}
                />
                <br/>

                <label>Percentual de gorjeta: </label>
                <input
                    type='number'
                    value={this.state.percGorjeta}
                    onChange={e => this.setState({ percGorjeta: Number(e.target.value) })}
                />
                <br/>
                <br/>

                <span>Total geral: {totalGeral.toFixed(2)}</span>
                <br/>

                <span>Total por pessoa: {totalPorPessoa.toFixed(2)}</span>

            </div>
        );
    }
}

export default Q2;