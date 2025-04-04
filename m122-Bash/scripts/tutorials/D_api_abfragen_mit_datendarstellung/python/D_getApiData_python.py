import requests
import json

# D_api_abfrage_mit_datendarstellung

# VARIABLES

# LINKS
GITLAB='https://gitlab.com/ch-tbz-it/Stud/m122/-/tree/main/10_Projekte_LB2/D_api-abfragen-mit-datendarstellung'

DIRECTORY='/mnt/c/Development/coding/Scripting/PROJECT-SCRIPTS/D_api_abfragen_mit_datendarstellung/bash'

def get_pokemon_data(pokemon_name):
    response = requests.get(f'https://pokeapi.co/api/v2/pokemon/{pokemon_name}')
    if response.status_code == 200:
        data = response.json()
        return data
    else:
        return None

pokemon_name = 'pikachu' 
data = get_pokemon_data(pokemon_name)

if data:
    print(f"Name: {data['name']}")
    print(f"Height: {data['height']}")
    print(f"Weight: {data['weight']}")
    print("Abilities:")
    for ability in data['abilities']:
        print(f"- {ability['ability']['name']}")
else:
    print(f"No data found for Pokémon: {pokemon_name}")
