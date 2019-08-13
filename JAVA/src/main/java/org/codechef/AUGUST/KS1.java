package org.codechef.AUGUST;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KS1 {
    private final Reader bufferedReader;

    private KS1() {
        bufferedReader = new Reader();
    }

    public static void main(String[] args) throws IOException {
        KS1 ks1 = new KS1();
        ks1.process();
    }

    private void process() throws IOException {
        int T = bufferedReader.nextInt();
        while (T-- > 0) {
            int N = bufferedReader.nextInt();
            long[] A = new long[N];


            long count = 0;
            for (int i = 0; i < N; i++) {
                A[i] = bufferedReader.nextLong();
            }

            long k = 0;

            Map<Long, Long> mapCount = new HashMap<>();
            Map<Long, Long> mapSum = new HashMap<>();

            mapCount.put(0L, 1L);
            mapSum.put(0L, 0L);

            for (int i = 0; i < N; i++) {
                k = k ^ A[i];
                if (!mapCount.containsKey(k)) {
                    mapCount.put(k, 1L);
                    mapSum.put(k, (long) (i + 1));
                } else {
                    count += mapCount.get(k) * i - mapSum.get(k);
                    mapCount.put(k, mapCount.get(k) + 1L);
                    mapSum.put(k, mapSum.get(k) + i + 1L);
                }
            }

            System.out.println(count);
        }
        bufferedReader.close();
    }

    private class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg) {
                return -ret;
            }
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) {
                return;
            }
            din.close();
        }
    }
}
