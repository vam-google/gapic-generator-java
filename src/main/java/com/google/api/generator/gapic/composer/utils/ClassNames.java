// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.api.generator.gapic.composer.utils;

import com.google.api.generator.gapic.model.Service;

/** Provides Gapic class names. */
public class ClassNames {
  // Using constants since many of these class names are used often.
  private static final String MOCK_SERVICE_CLASS_NAME_PATTERN = "Mock%s";
  private static final String MOCK_SERVICE_IMPL_CLASS_NAME_PATTERN = "Mock%sImpl";
  private static final String SERVICE_CLIENT_CLASS_NAME_PATTERN = "%sClient";
  private static final String SERVICE_CLIENT_TEST_CLASS_NAME_PATTERN = "%sClientTest";
  private static final String SERVICE_SETTINGS_CLASS_NAME_PATTERN = "%sSettings";
  private static final String SERVICE_STUB_SETTINGS_CLASS_NAME_PATTERN = "%sStubSettings";
  private static final String SERVICE_STUB_CLASS_NAME_PATTERN = "%sStub";
  private static final String TRANSPORT_SERVICE_STUB_CLASS_NAME_PATTERN = "%s%sStub";
  private static final String TRANSPORT_SERVICE_CALLABLE_FACTORY_CLASS_NAME_PATTERN =
      "%s%sCallableFactory";

  private final String transportPrefix;

  public ClassNames(String transportPrefix) {
    this.transportPrefix = transportPrefix;
  }

  public static String getServiceClientClassName(Service service) {
    return String.format(
        SERVICE_CLIENT_CLASS_NAME_PATTERN,
        monolithBackwardsCompatibleName(service.overriddenName()));
  }

  public static String getServiceSettingsClassName(Service service) {
    return String.format(
        SERVICE_SETTINGS_CLASS_NAME_PATTERN,
        monolithBackwardsCompatibleName(service.overriddenName()));
  }

  public static String getServiceStubSettingsClassName(Service service) {
    return String.format(
        SERVICE_STUB_SETTINGS_CLASS_NAME_PATTERN, monolithBackwardsCompatibleName(service.name()));
  }

  public static String getServiceStubClassName(Service service) {
    return String.format(
        SERVICE_STUB_CLASS_NAME_PATTERN, monolithBackwardsCompatibleName(service.name()));
  }

  public String getTransportServiceCallableFactoryClassName(Service service) {
    return String.format(
        TRANSPORT_SERVICE_CALLABLE_FACTORY_CLASS_NAME_PATTERN,
        transportPrefix,
        monolithBackwardsCompatibleName(service.name()));
  }

  public String getTransportServiceStubClassName(Service service) {
    return String.format(
        TRANSPORT_SERVICE_STUB_CLASS_NAME_PATTERN,
        transportPrefix,
        monolithBackwardsCompatibleName(service.name()));
  }

  public static String getServiceClientTestClassName(Service service) {
    return String.format(SERVICE_CLIENT_TEST_CLASS_NAME_PATTERN, service.overriddenName());
  }

  public static String getMockServiceClassName(Service service) {
    return String.format(MOCK_SERVICE_CLASS_NAME_PATTERN, service.name());
  }

  public static String getMockServiceImplClassName(Service service) {
    return String.format(MOCK_SERVICE_IMPL_CLASS_NAME_PATTERN, service.name());
  }

  private static String monolithBackwardsCompatibleName(String rawServiceName) {
    return rawServiceName.startsWith("IAMCredentials")
        ? rawServiceName.replace("IAM", "Iam")
        : rawServiceName;
  }
}
