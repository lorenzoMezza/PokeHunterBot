# PokeHunterBot

PokeHunterBot is a Java-based automation tool crafted to elevate your Pokémon gaming experience. Utilizing the Java Robot class, this bot sends precise inputs for character movement and in-game actions. Its primary focus is efficient detection and handling of shiny Pokémon encounters.

## Features and Methods

### Shiny Pokémon Detection

PokeHunterBot employs two robust methods for detecting shiny Pokémon encounters:

1. **Pixel Detection in Hard-Coded Paths:**
   - Default paths use pixel detection near the Pokémon's name.
   - Specific pixels are checked, signaling a shiny encounter.
   - Ensures accurate detection during shiny encounters.

2. **OCR in Customizable Paths:**
   - Customizable paths use Optical Character Recognition (OCR) with [Tesseract](https://github.com/nguyenq/tess4j).
   - Verifies the English word "shiny" during battles near the Pokémon's name.
   - Provides flexibility for users to define their paths.

### Anti-AFK Mode

When a shiny Pokémon is detected, PokeHunterBot enters Anti-AFK mode:
   - Human-like clicks simulate player activity, preventing AFK detection.
   - A sound alert notifies the player of the shiny encounter.
   - Leaves the responsibility of capturing the shiny Pokémon to the player.

### Randomized Timing and Natural Mouse Motions

Each action by PokeHunterBot is timed randomly to emulate human behavior:
   - Random intervals between actions enhance human-like interaction.
   - Mouse movements mimic natural motions, providing an authentic experience.

### Safety

PokeHunterBot prioritizes safety and user experience:
   - **Safety Measures:** Operates following randomitation and more human like interaction with the game to avoid sanctions.
   - **NaturalMouseMotion :** Special thanks to [NaturalMouseMotion](https://github.com/JoonasVali/NaturalMouseMotion)'s creator for enabling natural mouse motions in the bot.

## Additional Information

### Capacity and Advantages

- **Versatile Custom Paths:**
  - Users can create custom paths tailored to specific gameplay requirements.
  - Path customization allows flexibility and adaptability in different gaming scenarios.

- **Efficient Shiny Pokémon Handling:**
  - Pixel detection and OCR combination ensures accurate shiny Pokémon identification.
  - Anti-AFK mode optimizes the capture process, allowing players to intervene during shiny encounters.

- **Randomization for Human-Like Behavior:**
  - Randomized timing and mouse movements enhance the bot's human-like behavior, minimizing the risk of detection.

### Important Considerations

- **Policy Adherence:**
  - Users are advised to use PokeHunterBot responsibly and adhere to the game's policies.
  - Breaking game policies may result in sanctions, and usage is entirely at the user's risk.

- **System Requirements:**
  - Ensure Java 8 or higher is installed on your system for effective PokeHunterBot operation.

## Guides and Tutorial

- [YouTube Video Tutorial](#link-to-youtube-video-tutorial)

