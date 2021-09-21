# Breadcrumbs Mod (1.8.9)

Breadcrumb-like statistics UI for Minecraft 1.8.9.

This mod requires [Essential](essential.gg). It'll automatically be downloaded when you load up Minecraft for the first time.

## Configuration

Edit config/breadcrumbs.json, config UI is NYI/TODO.

### Example

```json
{
	"groups": [
		{
			"x": 1.0,
			"y": 1.0,
			"anchor": "BOTTOM_RIGHT",
			"breadcrumbs": [
				{
					"type": "COMBO",
					"label": "Combo",
					"config": {}
				},

				{
					"type": "SPEED",
					"label": "Speed",
					"config": {}
				}
			]
		}
	]
}
```