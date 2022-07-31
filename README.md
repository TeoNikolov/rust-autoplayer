# Rust Autoplayer

<p align="center">
  <img width="600" height="338" src="./demo.gif">
</p>

[HD Demo (YouTube)](https://www.youtube.com/watch?v=JtyWyVg8W8k)

The Rust Autoplayer app was a small personal project I did a long long time ago. The main purpose of the app was to solve a tedious, manual task, which I would often do in the game "Rust". When I wanted to raid enemy bases, I would often craft and equip melee tools, which I would use to break the enemy base's walls. The tools would break after prolonged use, requiring me to swap to a different melee weapon.

This Java app uses windows hooks to simulate mouse clicks and keyboard presses, bypassing the EAC anti-cheat system. I used basic computer vision processing to detect when a tool breaks, developed a custom UI to define the inventory layout and where tools are, and included some parameters and hotkeys for controlling the automatic process of weapon switching, and left-clicking.
