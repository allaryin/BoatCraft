package k2b6s9j.boatcraft.compatibility.ironchest

import cpw.mods.fml.common.network.IGuiHandler
import cpw.mods.ironchest.{ContainerIronChest, IronChestType}
import cpw.mods.ironchest.client.GUIChest
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import k2b6s9j.boatcraft.api.Boat
import net.minecraft.util.AxisAlignedBB
import cpw.mods.ironchest.TileEntityIronChest
import k2b6s9j.boatcraft.api.Boat

//TODO: Fill Documentation
/**
 *
 */
object IronChestsGuiHandler extends IGuiHandler
{
  //TODO: Fill Documentation
  /**
   *
   * @param ID
   * @param player
   * @param world
   * @param x
   * @param y
   * @param z
   * @return
   */
  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int) =
		GUIChest.GUI.buildGUI(IronChestType.values()(ID), player.inventory,
				getBoat(world, x, y, z).getInventory.asInstanceOf[TileEntityIronChest])

  //TODO: Fill Documentation
  /**
   *
   * @param ID
   * @param player
   * @param world
   * @param x
   * @param y
   * @param z
   * @return
   */
  override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int) =
		new ContainerIronChest(player.inventory, getBoat(world, x, y, z), IronChestType.values()(ID), 0, 0)

  //TODO: Fill Documentation
  /**
   *
   * @param world
   * @param x
   * @param y
   * @param z
   * @return
   */
  private def getBoat(world: World, x: Int, y: Int, z: Int) =
		world.getEntitiesWithinAABB(classOf[Boat.EntityBoatContainer],
				AxisAlignedBB getBoundingBox(x, y, z, x + 1, y + 1, z + 1))
				.get(0)
				.asInstanceOf[Boat.EntityBoatContainer]
}