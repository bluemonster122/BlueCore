package teamblep.blepcore.common.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.common.item.tools.ToolBase;
import teamblep.blepcore.common.network.AirClickMessage;

public class EventHandlerTool {

  public static final EventHandlerTool INSTANCE = new EventHandlerTool();

  private EventHandlerTool() {
  }

  @SubscribeEvent
  public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
    if (event.getSide() == Side.SERVER) {
      ItemStack itemStack = event.getItemStack();
      if (itemStack.getItem() instanceof ToolBase) {
        ToolBase item = (ToolBase) itemStack.getItem();
        EntityPlayer player = event.getEntityPlayer();
        EnumHand hand = event.getHand();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState blockState = world.getBlockState(pos);
        EnumFacing side = event.getFace();
        Vec3d hit = event.getHitVec();

        if (item.rightClickBlockAction(player, hand, world, pos, blockState, side, hit)) {
          event.setCancellationResult(EnumActionResult.SUCCESS);
        } else {
          event.setCancellationResult(EnumActionResult.PASS);
        }
        event.setCanceled(true);
      }
    }
  }

  @SubscribeEvent
  public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
    if (event.getSide() == Side.SERVER) {
      ItemStack itemStack = event.getItemStack();
      if (itemStack.getItem() instanceof ToolBase) {
        ToolBase item = (ToolBase) itemStack.getItem();
        EntityPlayer player = event.getEntityPlayer();
        EnumHand hand = event.getHand();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState blockState = world.getBlockState(pos);
        EnumFacing side = event.getFace();
        Vec3d hit = event.getHitVec();

        if (item.leftClickBlockAction(player, hand, world, pos, blockState, side, hit)) {
          event.setCancellationResult(EnumActionResult.SUCCESS);
        } else {
          event.setCancellationResult(EnumActionResult.PASS);
        }
        event.setCanceled(true);
      }
    }
  }

  @SubscribeEvent
  public void onRightClickAir(PlayerInteractEvent.RightClickItem event) {
    if (event.getSide() == Side.SERVER) {
      RayTraceResult rayTraceResult = event.getEntityPlayer().rayTrace(14D, 0);
      if (rayTraceResult == null || rayTraceResult.typeOfHit == Type.MISS) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof ToolBase) {
          ToolBase item = (ToolBase) itemStack.getItem();
          EntityPlayer player = event.getEntityPlayer();
          EnumHand hand = event.getHand();
          World world = event.getWorld();
          if (item.rightClickAirAction(player, hand, world)) {
            event.setCancellationResult(EnumActionResult.SUCCESS);
          } else {
            event.setCancellationResult(EnumActionResult.PASS);
          }
          event.setCanceled(true);
        }
      }
    }
  }

  @SubscribeEvent
  public void onLeftClickAir(PlayerInteractEvent.LeftClickEmpty event) {
    if (event.getSide() == Side.CLIENT) {
      BlepCore.net
          .sendToServer(new AirClickMessage(event.getEntityPlayer().getGameProfile().getId()));
    }
    if (event.getSide() == Side.SERVER) {
      ItemStack itemStack = event.getItemStack();
      if (itemStack.getItem() instanceof ToolBase) {
        ToolBase item = (ToolBase) itemStack.getItem();
        EntityPlayer player = event.getEntityPlayer();
        EnumHand hand = event.getHand();
        World world = event.getWorld();
        item.leftClickAirAction(player, hand, world);
        // TODO: possibly make it so that you can left click with teh left hand.
//                if () {
//                    event.setCancellationResult(EnumActionResult.SUCCESS);
//                } else {
//                    event.setCancellationResult(EnumActionResult.PASS);
//                }
//                event.setCanceled(true);
      }
    }
  }
}