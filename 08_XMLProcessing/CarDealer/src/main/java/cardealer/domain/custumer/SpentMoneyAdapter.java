package cardealer.domain.custumer;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class SpentMoneyAdapter extends TypeAdapter<Double> {

    @Override
    public void write(JsonWriter out, Double value) throws IOException {
        out.value(String.format("%.2f",value));
    }

    @Override
    public Double read(JsonReader in) throws IOException {
        return null;
    }
}
