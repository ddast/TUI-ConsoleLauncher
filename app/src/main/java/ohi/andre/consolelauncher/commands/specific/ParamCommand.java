package ohi.andre.consolelauncher.commands.specific;

import android.util.Log;

import ohi.andre.consolelauncher.R;
import ohi.andre.consolelauncher.commands.CommandAbstraction;
import ohi.andre.consolelauncher.commands.ExecutePack;
import ohi.andre.consolelauncher.commands.main.Param;

/**
 * Created by francescoandreuzzi on 01/05/2017.
 */

public abstract class ParamCommand implements CommandAbstraction {

    @Override
    public final int[] argType() {
        return new int[] {CommandAbstraction.PARAM};
    }

    @Override
    public final String exec(ExecutePack pack) throws Exception {
        String o = doThings(pack);
        if(o != null) return o;

        try {
            return paramForString(pack.get(String.class, 0)).exec(pack);
        } catch (NullPointerException e) {
            return pack.context.getString(R.string.output_invalid_param);
        }
    }

    public final int[] argsForParam(String param) {
        try {
            return paramForString(param).args();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public abstract String[] params();
    protected abstract Param paramForString(String param);
    protected abstract String doThings(ExecutePack pack);
}
