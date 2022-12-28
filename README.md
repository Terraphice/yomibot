# Your Only Move Is Hustle Discord Bot

yomibot is a Discord bot designed to provide utility and 
administrative functions for the official Your Only Move is 
HUSTLE server.

## Features

Some examples of features that yomibot may include are:

- Automated responses to certain keywords or phrases
- Commands for looking up game-related information
- Tools for moderating the server, such as the ability to mute or kick users
- Customizable welcome messages for new members

## Usage

To use yomibot, you can use slash commands by typing `/command`. 
For example, you can use commands like `/kick @user`.

## Contributing

If you would like to contribute to the development of yomibot, 
please feel free to fork the repository and submit pull requests. 
We welcome any bug fixes, feature suggestions, or improvements to the codebase.

Please be careful when creating database tables. 
Modifying a table after it has been created will result in 
all previous entries having empty values for the new columns. 
This means that using constraints such as `NOT NULL` are not 
possible, and you will need to account for the possibility of 
empty values being returned when using any "new" columns.

## License

yomibot is not currently licensed. A license will be added in the future.
