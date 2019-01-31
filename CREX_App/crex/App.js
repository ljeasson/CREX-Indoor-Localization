import React, { Component } from 'react';
import { Text, View, StyleSheet, Alert, ActivityIndicator, Switch, Image } from 'react-native';
import { Constants, Location, Permissions, Accelerometer } from 'expo';

// You can import from local files
{ /*import AssetExample from './components/AssetExample';*/}

// or any pure javascript modules available in npm
{/*import { Card } from 'react-native-elements';*/} // Version can be specified in package.json

export default class App extends Component {
  state = {
    locationResult: null,
    accelerometerData: { x: 0, y: 0, z: 0 },

    mapRegion: {
      latitude: 37.78825,
      longitude: -122.4324,
      latitudeDelta: 0.0922,
      longitudeDelta: 0.0421,
    },

    inputValue: "You can change me!",
    switchValue: true
  };

  componentWillUnmount() {
    this._unsubscribeFromAccelerometer();
  }

  componentDidMount() {
    this._getLocationAsync();
    this._subscribeToAccelerometer();
    this._getLocationAsync();
  }

  _handleStartButtonPress = () => {
    Alert.alert('Tracking...');
  };

  _handleStopButtonPress = () => {
   Alert.alert('Tracking Terminated...');
 };

  _getLocationAsync = async () => {
    let { status } = await Permissions.askAsync(Permissions.LOCATION);
    if (status !== 'granted') {
      this.setState({
        locationResult: 'Permission to access location was denied',
      });
    }

    let location = await Location.getCurrentPositionAsync({});
    this.setState({ locationResult: JSON.stringify(location) });
  };

  _subscribeToAccelerometer = () => {
    this._acceleroMeterSubscription = Accelerometer.addListener(
      accelerometerData => this.setState({ accelerometerData })
    );
  };

  _unsubscribeFromAccelerometer = () => {
    this._acceleroMeterSubscription && this._acceleroMeterSubscription.remove();
    this._acceleroMeterSubscription = null;
  };

  _handleMapRegionChange = mapRegion => {
    this.setState({ mapRegion });
  };

  _handleTextChange = inputValue => {
    this.setState({ inputValue });
  };

  _handleToggleSwitch = () => this.setState(state => ({
    switchValue: !state.switchValue,
    tracking: !state.tracking
  }));

  render() {
    return (
      <View style={styles.container}>

        {/*<Text style={styles.paragraph}>
          Change code in the editor and watch it change on your phone!
          Save to get a shareable url.
        </Text>*/}
        
        <Text style={styles.header}>
          CREX Location Tracking App
        </Text>

        <Image source={require('./Dino.jpeg')}/>
        
        <Text style={styles.paragraph}>
          Enable Switch to Start Tracking
        </Text>
        
        <Switch
          onValueChange={this._handleToggleSwitch}
          value={this.state.switchValue}
        />
        
        <Text style={styles.paragraph}>
          Diasable to Stop Tracking
        </Text>
      
        { !this.state.tracking &&
        <ActivityIndicator size="large" />
        }
        
        { !this.state.tracking &&
        <Text style={styles.data}>
          Accelerometer:
          x = {this.state.accelerometerData.x.toFixed(2)}{', '}
          y = {this.state.accelerometerData.y.toFixed(2)}{', '}
          z = {this.state.accelerometerData.z.toFixed(2)}
        </Text>
        }

        { !this.state.tracking &&
        <Text style={styles.data}>
          Location: {this.state.locationResult}
        </Text>
        }
        
        {/*<Card title="Local Modules">
          <AssetExample />
        </Card>*/}
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingTop: Constants.statusBarHeight,
    backgroundColor: '#ecf0f1',
  },
  header: {
    margin: 20,
    fontSize: 25,
    fontWeight: 'bold',
    textAlign: 'center',
    color: '#34495e',
  },
  paragraph: {
    margin: 20,
    fontSize: 18,
    fontWeight: 'bold',
    textAlign: 'center',
    color: '#34495e',
  },
  data: {
    margin: 20,
    fontSize: 14,
    fontWeight: 'bold',
    textAlign: 'center',
    color: '#34495e',
  },
});
