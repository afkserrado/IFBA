import { Component } from 'react';

class App extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            status: false,
        };
    }

    logar(status) {
        this.setState({ status: status });
    }

    render() {
        return(
            <div>
                {this.state.status 
                ? 
                <div>
                    <h1>Olá, Manoel.</h1>
                    <button onClick = {() => this.logar(false)}>Logout</button>
                </div>
                :
                <div>
                    <h1>Olá, visitante. Faça seu login:</h1>
                    <button onClick = {() => this.logar(true)}>Login</button>
                </div>
                }
            </div>
        )
    }
} export default App;