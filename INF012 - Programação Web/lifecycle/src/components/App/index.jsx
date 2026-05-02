import { Component } from 'react';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            hora: new Date().toLocaleTimeString,
            contador: 0
        };
    }

    // Sobrescrita
    componentDidMount() {
        setInterval(() => {
            this.setState({ hora: new Date().toLocaleTimeString() });
            this.setState({ contador: this.state.contador + 1 });
        }, 1000);
    }

    render() {
        return(
            <div>
                <h1>Hora:</h1>
                <h1>{this.state.hora}</h1>
                <h1>{this.state.contador}</h1>
            </div>
        )
    }
} export default App;