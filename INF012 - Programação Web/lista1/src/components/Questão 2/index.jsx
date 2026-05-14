import React, { Component } from 'react';

class Q2 extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            consumo: '',
            qtdPessoas: '',
            percGorjeta: '',
        };
    }

    render() {
        
        const { 
            consumo: consumoStr, 
            qtdPessoas: qtdPessoasStr, 
            percGorjeta: percGorjetaStr 
        } = this.state;

        // Converte as strings para Number
        const consumo = Number(consumoStr || 0);
        const qtdPessoas = Number(qtdPessoasStr || 0);
        const percGorjeta = Number(percGorjetaStr || 0);

        const totalGeral = consumo * (1 + percGorjeta / 100);
        const totalPorPessoa = qtdPessoas > 0 ? totalGeral / qtdPessoas : 0;
        
        return(
            <div>
                <h1>Questão 2</h1>

                <label>Consumo: </label>
                <input 
                    type='number' 
                    value={consumoStr} 
                    onChange={e => this.setState({ consumo: e.target.value })}
                />
                <br/>

                <label>Quantidade de pessoas: </label>
                <input
                    type='number'
                    value={qtdPessoasStr}
                    onChange={e => this.setState({ qtdPessoas: e.target.value })}
                />
                <br/>

                <label>Percentual de gorjeta: </label>
                <input
                    type='number'
                    value={percGorjetaStr}
                    onChange={e => this.setState({ percGorjeta: e.target.value })}
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