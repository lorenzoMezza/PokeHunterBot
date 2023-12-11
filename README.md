# PokeHunterBot

PokeHunterBot is a Java-based bot designed to streamline interactions in the Pokemon game world. It utilizes the Java Robot class to send inputs, providing users with a tool to simplify their shiny hunting experience.

## Detection Methods

The bot employs two methods to identify shiny Pokemon encounters:

1. **Default Hard-Coded Paths:**
   - Verifies specific pixels near the Pokemon's name, indicating a shiny encounter.

2. **Customizable Paths:**
   - Utilizes OCR (Optical Character Recognition) from [Tesseract Releases](https://github.com/tesseract-ocr/tessdata/releases) to detect the word "shiny" during battles near the Pokemon's name.

## Anti-AFK Mode

When a shiny Pokemon is encountered, the bot enters anti-AFK mode, simulating human-like clicks to emulate a player idling. A sound alert notifies the player of the shiny Pokemon, leaving the responsibility of capture to the user.

## Acknowledgments

Special thanks to [NaturalMouseMotion](https://github.com/JoonasVali/NaturalMouseMotion.git) for providing the means to achieve natural mouse motions in the bot process.

## How to Use

Refer to the [GUIDE.pdf](https://github.com/LorenzoMezza/PokeHunterBot/files/13631786/GUIDE.pdf) for a comprehensive guide on using the bot.

## Creating Custom Paths

Explore the [CUSTOMPATHGUIDE.pdf](https://github.com/LorenzoMezza/PokeHunterBot/files/13631790/CUSTOMPATHGUIDE.pdf) for instructions on creating custom paths.

## Video Tutorial

Watch the video tutorial on [YouTube](https://www.youtube.com/watch?v=NeBkJnjdOfA) for a visual walkthrough of PokeHunterBot in action.
