package k2b6s9j.BoatCraft.registry

import cpw.mods.fml.common.registry.GameRegistry
import k2b6s9j.BoatCraft.utilities.log.ModLogger
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{ShapelessRecipes, ShapedRecipes, CraftingManager, IRecipe}
import net.minecraftforge.oredict.ShapedOreRecipe
import net.minecraftforge.oredict.ShapelessOreRecipe

import java.util

object RecipeRegistration {

  def RemoveRecipe(resultItem: ItemStack) {
    var recipeResult: ItemStack = null
    val recipes = CraftingManager.getInstance.getRecipeList.asInstanceOf[util.ArrayList[Any]]
    for (scan <- 0 until recipes.size) {
      val tmpRecipe = recipes.get(scan).asInstanceOf[IRecipe]
      if (tmpRecipe.isInstanceOf[ShapedRecipes])
      {
        val recipe: ShapedRecipes = tmpRecipe.asInstanceOf[ShapedRecipes]
        recipeResult = recipe.getRecipeOutput
      }

      if (tmpRecipe.isInstanceOf[ShapelessRecipes])
      {
        val recipe: ShapelessRecipes = tmpRecipe.asInstanceOf[ShapelessRecipes]
        recipeResult = recipe.getRecipeOutput
      }

      if (ItemStack.areItemStacksEqual(resultItem, recipeResult))
      {
        ModLogger.info("Removed Recipe: " + recipes.get(scan) + " -> " + recipeResult)
        recipes.remove(scan)
      }
    }
  }

  def AddRecipe(item: ItemStack, parts: AnyRef*) {
    GameRegistry.addRecipe(new ShapedOreRecipe(item, parts))
  }

  def AddShapelessRecipe(item: ItemStack, parts: AnyRef*) {
    CraftingManager.getInstance().getRecipeList.asInstanceOf[util.ArrayList[Any]].add(new ShapelessOreRecipe(item, parts))
  }
}
