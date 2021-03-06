import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.screens.options.DropdownMenu;
import java.util.ArrayList;
import java.util.Arrays;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import basemod.ReflectionHacks;
import sayTheSpire.Output;
import sayTheSpire.ui.DropdownElement;
import sayTheSpire.ui.UIRegistry;

public class DropdownMenuPatch {

  @SpirePatch(clz = DropdownMenu.class, method = "update")
  public static class updatePatch {

    public static void Postfix(DropdownMenu __instance) {
      if (__instance.getHitbox().justHovered) {
        DropdownElement currentDropdown = new DropdownElement(__instance);
        UIRegistry.register(__instance, currentDropdown);
        Output.setUI(currentDropdown);
      }
    }
  }

  @SpirePatch(clz = DropdownMenu.class, method = "updateNonMouseStartPosition")
  public static class UpdateNonMouseStartPositionPatch {

    public static void Postfix(DropdownMenu __instance) {
      DropdownElement currentDropdown = (DropdownElement) UIRegistry.getUI(__instance);
      if (currentDropdown != null) {
        Output.text(currentDropdown.getStatusString(), true);
      }
    }
  }

  @SpirePatch(clz = DropdownMenu.class, method = "updateNonMouseInput")
  public static class UpdateNonMouseInputPatch {

    public static void Postfix(DropdownMenu __instance) {
      DropdownElement currentDropdown = (DropdownElement) UIRegistry.getUI(__instance);
      if (currentDropdown == null) return;
      int targetIndex = currentDropdown.getIndex();
      if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) targetIndex--;
      else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) targetIndex++;
      else return;
      if (targetIndex < 0) targetIndex = currentDropdown.getOptionCount() - 1;
      if (targetIndex >= currentDropdown.getOptionCount()) targetIndex = 0;
      currentDropdown.setIndex(targetIndex);
      Output.text(currentDropdown.getStatusString(), true);
    }

  }
}
