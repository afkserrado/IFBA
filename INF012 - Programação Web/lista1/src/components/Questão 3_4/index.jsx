import React, { Component } from 'react';

class Q3_4 extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            numUser: '',
            mensagem: '',
            tentativas: 0
        };

        this.numSorted = 0;
        this.min = 0;
        this.max = 100;
        this.compare = this.compare.bind(this);
    }

    componentDidMount() {
        this.numSorted = this.sort();
    }

    sort(min = 0, max = 100) {
        const numSorted = Math.floor(Math.random() * (max - min + 1)) + min;
        console.log(`numSorted: ${numSorted}`);
        return numSorted;
    }

    compare() {
        const numUser = Number(this.state.numUser);
        const numSorted = Number(this.numSorted);

        if(numUser === numSorted) {
            this.setState({ 
                numUser: '',
                mensagem: `Parabéns, você ganhou! O número sorteado é ${this.numSorted}, e você precisou de ${this.state.tentativas} tentativas para encontrá-lo.`,
                tentativas: 0
            });

            this.numSorted = this.sort();
        }
        else if(numUser > numSorted) {
            this.setState({ mensagem: 'Seu número é maior que o sorteado.' })
        }
        else {
            this.setState({ mensagem: 'Seu número é menor que o sorteado.' })
        }
    }
    
    render() {
        return(
            <div>
                <h1>Questões 3 e 4</h1>
                
                <span>Digite um número inteiro de 0 a 100: </span>
                <input 
                    type='number' 
                    value={this.state.numUser} 
                    onChange={(e) => this.setState({ numUser: e.target.value })}
                />

                <button onClick={() => {
                    this.setState({ tentativas: this.state.tentativas += 1 })
                    this.compare();
                }}>
                    Tentar
                </button>
                <p>{this.state.mensagem}</p>
            </div>
        );
    }
}

export default Q3_4;