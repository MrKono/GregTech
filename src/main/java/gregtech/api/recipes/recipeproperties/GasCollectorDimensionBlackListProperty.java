package gregtech.api.recipes.recipeproperties;

import gregtech.api.worldgen.config.WorldGenRegistry;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class GasCollectorDimensionBlackListProperty extends RecipeProperty<IntList> {

    public static final String KEY = "dimensionBlackList";

    private static GasCollectorDimensionBlackListProperty INSTANCE;

    private GasCollectorDimensionBlackListProperty() {
        super(KEY, IntList.class);
    }

    public static GasCollectorDimensionBlackListProperty getInstance() {
        if (INSTANCE == null)
            INSTANCE = new GasCollectorDimensionBlackListProperty();
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("gregtrch.recipe.dimensins.blacklist",
                getDimensionsForRecipe(castValue(value))), x, y, color);
    }

    private static String getDimensionsForRecipe(IntList value) {
        Int2ObjectMap<String> dimNames = WorldGenRegistry.getNamedDimensions();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < value.size(); i++) {
            builder.append(dimNames.getOrDefault(value.getInt(i), String.valueOf(value.getInt(i))));
            if (i != value.size() - 1)
                builder.append(", ");
        }
        String str = builder.toString();

        if (str.length() >= 13) {
            str = str.substring(0, 10) + "..";
        }
        return str;
    }
}
