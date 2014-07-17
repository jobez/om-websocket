# om-websocket

You pass om-websocket the following attributes as opts:

- connection string (usually something like  ```(str "ws://" js/location.host "/ws" )``` )
- an **in-coord** function 
  * this function is passed a channel, the main data cursor, and owner
  * it is meant to be used to coordinate what to do when messages are received *from the server to the client*
- an **out-coord** function
  * this function is passed a channel, the main data cursor, the owner, and the websocket
  * it is meant to be used to coordinate messages *from your app to the server*
  * it requires a shared state channel associated to the key :websocket->

## Usage

FIXME

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
