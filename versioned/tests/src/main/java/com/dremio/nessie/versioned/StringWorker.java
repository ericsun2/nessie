/*
 * Copyright (C) 2020 Dremio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dremio.nessie.versioned;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.stream.Stream;

import javax.annotation.Nonnull;

import com.dremio.nessie.versioned.AssetKey.NoOpAssetKey;
import com.google.protobuf.ByteString;

/**
 * ValueWorker implementation for {@code String class}. Can also be used as simple {@link Serializer}.
 */
public final class StringWorker implements ValueWorker<String> {
  private static final ValueWorker<String> INSTANCE = new StringWorker();

  private StringWorker() {
  }

  /**
   * Get a instance of a string serializer.
   * @return the instance
   */
  @Nonnull
  public static ValueWorker<String> getInstance() {
    return INSTANCE;
  }

  @Override
  public String fromBytes(ByteString bytes) {
    return bytes.toString(UTF_8);
  }

  @Override
  public ByteString toBytes(String value) {
    return ByteString.copyFrom(value, UTF_8);
  }

  @Override
  public Stream<AssetKey> getAssetKeys(String value) {
    return Stream.of();
  }

  @Override
  public Serializer<AssetKey> getAssetKeySerializer() {
    return NoOpAssetKey.SERIALIZER;
  }
}
