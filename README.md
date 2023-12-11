# PokeHunterBot
This bot utilizes the Java Robot class to send the necessary inputs for moving the character and performing specific actions in the game world. It employs two methods to detect when a shiny Pokemon is encountered. The first method, used in the default hard-coded paths, simply checks if specific pixels appear on the screen near the Pokemon's name, which only occurs in the case of a shiny encounter. The second method, used in customizable paths, employs OCR (Optical Character Recognition) from [https://github.com/tesseract-ocr/tessdata/releases](https://github.com/tesseract-ocr/tessdata/releases)
to verify if the English word "shiny" appears on the screen during the battle near pokemon's name. When a shiny Pokemon is encountered, the bot enters anti-AFK mode by simulating human-like clicks to emulate a player wasting time. Simultaneously, it emits a sound alert to notify the player that a shiny Pokemon has been found, leaving the responsibility of capturing it to the player.
Each action performed by the bot is timed randomly to emulate a human, including the mouse movements. I would like to express gratitude to https://github.com/JoonasVali/NaturalMouseMotion.git for providing the means to achieve natural mouse motions in the bot process.

How to use the bot guide: [GUIDE.pdf](https://github.com/LorenzoMezza/PokeHunterBot/files/13631786/GUIDE.pdf)

How to create custrom Paths guide: [CUSTOMPATHGUIDE.pdf](https://github.com/LorenzoMezza/PokeHunterBot/files/13631790/CUSTOMPATHGUIDE.pdf)

Youtube video tutorial: https://www.youtube.com/watch?v=NeBkJnjdOfA
