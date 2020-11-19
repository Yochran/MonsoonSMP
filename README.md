# MonsoonSMP
A Smaller, but more organized revamped SMP core for Monsoon. (Not completed). This core is designed by me, for the players, based off of their pure suggestions. (At least the non-stupid, accepted ones.)

# Info:
### Commands:
  - /afk
  - /deaths
  - /monsoon
  - /resetdeaths
  
### Listeners:
  - AFK Listener, tracks player movement/chatting to unset player's afk statuses.
  - Bed listener, if 30% of all non-afk'd online players are in a bed, it will set the time to day.
  - Death listener, if a player dies, it adds one to their death count, and the server death count.
  - Player log listener, that sets the player's Name and UUID in the data file, as well as their death count to 0 (if they have never joined).
  
### Runnables:
  - AFK Runnable. This is the actual timer that sets a player's status to AFK if they have not moved for five minutes.
  - Phanthom runnable. This is the constant task that kills all phantoms in the main world.
  
This plugin is dependent on vNitrogen, for setting player's displaynames as their rank colors in the commands.
