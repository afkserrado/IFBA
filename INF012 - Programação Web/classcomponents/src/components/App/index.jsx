import React, { Component } from 'react';
import Equipe from '../Equipe'

class App extends Component {
    render() {
        return(
            <div>
                <h1> Conheça a nossa equipe: </h1>
                    <Equipe nome="Manoel"/>
            </div>
        );
    }
} export default App;