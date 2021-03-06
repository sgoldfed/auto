/*
 * Copyright (C) 2013 Google, Inc.
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
package com.google.testing.compile;

import javax.annotation.processing.Processor;
import javax.tools.JavaFileObject;

import org.truth0.FailureStrategy;
import org.truth0.subjects.SubjectFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * A <a href="https://github.com/truth0/truth">Truth</a> {@link SubjectFactory} for creating
 * {@link JavaSourcesSubject} instances.
 *
 * @author Gregory Kick
 */
public class JavaSourcesSubjectFactory
    extends SubjectFactory<JavaSourcesSubject, Iterable<? extends JavaFileObject>> {
  public static JavaSourcesSubjectFactory javaSources() {
    return new JavaSourcesSubjectFactory(ImmutableList.<Processor>of());
  }

  public static JavaSourcesSubjectFactory javaSourcesProcessedWith(Processor first,
      Processor... rest) {
    return new JavaSourcesSubjectFactory(ImmutableList.copyOf(Lists.asList(first, rest)));
  }

  private final ImmutableList<Processor> processors;

  private JavaSourcesSubjectFactory(ImmutableList<Processor> processors) {
    this.processors = processors;
  }

  @Override
  public JavaSourcesSubject getSubject(FailureStrategy failureStrategy,
      Iterable<? extends JavaFileObject> subject) {
    return new JavaSourcesSubject(failureStrategy, subject, processors);
  }
}
