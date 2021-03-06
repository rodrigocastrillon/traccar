package org.traccar.protocol;

import io.netty.handler.codec.http.HttpMethod;
import org.junit.Test;
import org.traccar.ProtocolTest;

public class SigfoxProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        SigfoxProtocolDecoder decoder = new SigfoxProtocolDecoder(null);

        verifyPosition(decoder, request(HttpMethod.POST, "/",
                buffer("{ \"device\": \"1CEDCE\", \"payload\": \"2002419b4a91c2c6580e0564\", \"time\": 1559924939, \"seqNumber\": 87 }")));

        verifyPosition(decoder, request(HttpMethod.POST, "/",
                buffer("%7B++%22device%22%3A%222BF839%22%2C++%22time%22%3A1510605882%2C++%22duplicate%22%3Afalse%2C++%22snr%22%3A45.61%2C++%22station%22%3A%2235A9%22%2C++%22data%22%3A%2200bd6475e907398e562d01b9%22%2C++%22avgSnr%22%3A45.16%2C++%22lat%22%3A-38.0%2C++%22lng%22%3A145.0%2C++%22rssi%22%3A-98.00%2C++%22seqNumber%22%3A228+%7D=")));

    }

}
